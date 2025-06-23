import React, { useState } from 'react';
import { type TaskCardProps } from './SideBar';
const TASK_URL: string = 'http://localhost:8080/tasks';

interface Props extends TaskCardProps{
  className:string
  delTask: (title:string) => void;
  addTask: (obj:TaskCardProps) => void;
}

const TaskCard: React.FC<Props> = (props:Props) => {
  const dateObj = new Date(props.dueDate);
  const formattedDate: string = dateObj.toLocaleDateString('en-GB', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
  });

  const deleteTask = async (titleName:string) => {
    const URL = TASK_URL + `/${titleName}`;
    const responce = await fetch(URL, {
      method: 'DELETE',
    });

    if(responce.ok === true)
    {
      
    }
    else{
      console.log(`Cant't delete a task.`);
    }
  };

  const changeTitle = () => {

  };

  const [isHovered, setIsHovered] = useState<boolean>(false);

  return (
    <div onMouseEnter={() => setIsHovered(true)} onMouseLeave = {() => setIsHovered(false)} className = {props.className}>
      <div className = "flex justify-between">
        <p className = "title justify-end">{props.title}</p>

        {isHovered && 
        <div className = "flex justify-end">
          <button type = "button" className = "btn btn-outline-light" onClick = {() => deleteTask(props.title)}>Delete Task</button>
          <button type = "button" className = "btn btn-outline-dark" onClick = {() => changeTitle()}>Change Title</button>
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