import React, { useEffect, useState} from 'react';
import { DAYS_URL, TASK_URL } from './App';
import './Loading.css';

type Props = {
    setTaskCount: React.Dispatch<React.SetStateAction<number>>,
    setTaskCompletedCount: React.Dispatch<React.SetStateAction<number>>,
}

type TasksToComplete = {
    title: string,
    status: boolean,
}

const TasksToComplete:React.FC<Props> = (props : Props) => {

    const [tasksToComplete, setTasksToComplete] = useState<TasksToComplete[]>([]);
    const todaysDayIndex = new Date().getDay();
    const weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];

    useEffect(() => {
  const currentDay = weekday[todaysDayIndex].toUpperCase();

  const fetchTasks = async () => {
    try {

      await fetch(`${TASK_URL}/${currentDay}/status-reset`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' }
      });

      const res = await fetch(`${TASK_URL}/${currentDay}`);
      if (!res.ok) throw new Error(`Failed to fetch: ${res.status}`);
      const data: TasksToComplete[] = await res.json();

      setTasksToComplete(data);
      props.setTaskCount(data.length);
      props.setTaskCompletedCount(data.filter(task => !task.status).length);

    } catch (err) {
      console.error('Error in fetching/resetting tasks:', err);
    }
  };

  fetchTasks();
}, []);

    const markComplete = async (task:string, index: number) => {
        const UPDATE_DAY_STATUS_URL = DAYS_URL + `/status/${weekday[todaysDayIndex].toUpperCase()}/${task}`;
        const responce = await fetch(UPDATE_DAY_STATUS_URL, {
            method: 'PATCH',
        });

        if(!responce.ok){
            console.log("Can't update tasks status");
        }

        setTasksToComplete(arr => arr.splice(index));
    }

    return (
        <div>
            <div>Tasks for Today ({weekday[todaysDayIndex]}):</div>
            {tasksToComplete.length > 0 ? 
            (
                tasksToComplete.map((task, i) => 
                {
                    return (
                        <div key = {i} className = {task.status ? 'task-red' : 'task-green'}>
                            <div key={i}>{task.title}</div>
                            <div key={i + 69}>{task.status}</div>
                            {!task.status &&
                            <button type = "button" onClick = {() => markComplete(task.title, i)}>Mark Complete</button>
                            }
                        </div>
                    );
                })
            ) : 
            (
                <div>No tasks to complete for today!</div>
            )}
        </div>
    );     
}

export default TasksToComplete;