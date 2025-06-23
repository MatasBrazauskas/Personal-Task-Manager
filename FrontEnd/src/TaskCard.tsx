import React, { useState } from 'react';
import { type TaskCardProps } from './App';
import { URL } from './App';

interface Props{
  className:string
  obj: TaskCardProps,
  setTasks: React.Dispatch<React.SetStateAction<TaskCardProps[]>>;
}

const TaskCard: React.FC<Props> = (props:Props) => {

  const deleteTask = async (titleName:string) => {
    const DELETE_URL = URL + `/${titleName}`;
    const responce = await fetch(DELETE_URL, {
      method: 'DELETE',
    });

    if(responce.ok === true)
    {
      console.log("Deleteing a task");
      props.setTasks(prevTasks => prevTasks.filter(task => task.title !== titleName));
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
        <p className = "title justify-end">{props.obj.title}</p>

        {isHovered && 
        <div className = "flex justify-end">
          <button type = "button" className = "btn btn-outline-light" onClick = {() => deleteTask(props.obj.title)}>Delete Task</button>
          <button type = "button" className = "btn btn-outline-dark" onClick = {() => changeTitle()}>Change Title</button>
        </div>
        }
      </div>
      <div className = "flex justify-start">
        <p className = "status">{props.obj.status ? 'Completed' : 'Pending'}</p>
        <p className = "date">{props.obj.dueDate}</p>
      </div>
    </div>
  );
};

export default TaskCard;