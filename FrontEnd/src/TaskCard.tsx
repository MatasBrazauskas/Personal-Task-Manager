import React, { useState } from 'react';
const TASK_URL: string = 'http://localhost:8080/tasks';

export interface TaskCardProps{
    className: string
    title: string,
    status: boolean,
    dueDate: string,
}

const TaskCard: React.FC<TaskCardProps> = (props:TaskCardProps) => {
  const dateObj = new Date(props.dueDate);
  const formattedDate: string = dateObj.toLocaleDateString('en-GB', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
  });

  const deleteTask = async (titleName:string) => {
    const URL = TASK_URL + `/${titleName}`;
    console.log(URL);
    const responce = await fetch(URL, {
      method: 'DELETE',
    });

    if(responce.ok === false)
    {
      console.log(`Cant't delete a task.`);
    }

  };

  const [isHovered, setIsHovered] = useState<boolean>(false);

  return (
    <div onMouseEnter={() => setIsHovered(true)} onMouseLeave = {() => setIsHovered(false)} className = {props.className}>
      <div className = "flex justify-between">
        <p className = "title justify-end">{props.title}</p>

        {isHovered && 
        <div className = "flex justify-end">
          <button type = "button" className = "btn btn-outline-light" onClick = {() => deleteTask(props.title)}>Delete Task</button>
          <button type = "button" className = "btn btn-outline-dark">Change Title</button>
        </div>
        }
      </div>
      <div className = "flex justify-start">
        <p className = "status">{props.status ? 'Completed' : 'Pending'}</p>
        <p className = "date">{formattedDate}</p>
      </div>
    </div>
  );
};

export default TaskCard;