import React, { useState, useEffect } from 'react';
import TaskCard from './TaskCard';
import "./TaskCard.css";

export interface TaskCardProps{
    title: string,
    status: boolean,
    dueDate: string,
}

const ADD_TASK_KEY:string = import.meta.env.ADD_NEW_TASK_MESSAGE;
const TASK_URL: string = 'http://localhost:8080/tasks';

interface Props{
    className: string;
    selectedComponent: (component: string) => void;
}

const SideBar: React.FC<Props> = (props: Props) =>
{
    const [tasks, setTasks] = useState<TaskCardProps[]>([]);

    useEffect(() => {
        const fetchedTasks = async () => {
            const responce = await fetch(TASK_URL);

            if(responce.ok === false)
            {
                console.log("Cant get the data");
                return;
            }

            const result: TaskCardProps[] = await responce.json();
            console.log(result);
            setTasks(result);
        }
        fetchedTasks();
    }, []);

    const deleteTask = (title:string) => {
        tasks.filter(obj => obj.title === title);
    }
    const addTask = (obj: TaskCardProps) => {
        tasks.push(obj);
    }

    return (
        <div className = {props.className}>
            <div>
                {tasks.map((task, index) => (
                    <TaskCard
                        className = "task"
                        key={index}
                        title={task.title}
                        status={task.status}
                        dueDate={task.dueDate}
                        delTask = {deleteTask}
                        addTask = {addTask}
                    />
                ))}
            </div>
            <div>
                <button type = "button" className = "btn btn-outline-secondary" onClick={() => props.selectedComponent(ADD_TASK_KEY)}>Add New Task</button>
            </div>
        </div>
    );
};

export default SideBar;
