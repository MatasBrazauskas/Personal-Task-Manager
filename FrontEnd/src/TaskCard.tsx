import React, { useState } from 'react';
import { DAYS_URL, type TaskCardProps } from './App';
import { TASK_URL } from './App';

interface Props{
  className:string
  obj: TaskCardProps,
  setTasks: React.Dispatch<React.SetStateAction<TaskCardProps[]>>;
}

const TaskCard: React.FC<Props> = (props:Props) => {

  const [changedTitle, setChangedTitle] = useState<string | null>(null);

  const deleteTask = async (titleName:string) => {
      const responce = await fetch(TASK_URL + `/${titleName}`, {
        method: 'DELETE',
        headers: {
              "Content-Type": "application/json",
          },
        body: JSON.stringify({titleName:titleName}),
      });

      if(!responce.ok){
        console.log('Cant delete a task');
      }

      const newResponce = await fetch(DAYS_URL + `/${titleName}`,{
        method: 'DELETE',
      });

      if(!newResponce.ok){
        console.log("Can't delete days");
      }

      props.setTasks(arr => arr.filter(i => i.title !== titleName));
  };

  const changeTitle = async () => {

      const PATCH_URL = TASK_URL + `/${changedTitle}/${props.obj.title}`;
      
      const taskResponce = await fetch(PATCH_URL, {
          method:'PATCH'
      });

      const daysResponce = await fetch(PATCH_URL, {
        method:'PATCH'
      });

      if(taskResponce.ok === false || daysResponce.ok === false){
        console.log("Error wit changing titles");
      }

      setChangedTitle(null);
      const newTitle:string = changedTitle === null ? '': changedTitle; 
      props.setTasks(arr => arr.map(obj => {
          if(obj.title === props.obj.title){
              return {...obj, title:newTitle};
          }
          return obj;
      }));
  };

  const [isHovered, setIsHovered] = useState<boolean>(false);

  return (
    <div onMouseEnter={() =>setIsHovered(true)} onMouseLeave = {() => setIsHovered(false)} className = {props.className}>
      {changedTitle === null ? 
      <>
      <div className = "flex justify-between">
        <p className = "title justify-end">{props.obj.title}</p>

        {isHovered && 
        <div className = "flex justify-end">
          <button type = "button" className = "btn btn-outline-light" onClick = {() => deleteTask(props.obj.title)}>Delete Task</button>
          <button type = "button" className = "btn btn-outline-dark" onClick = {() => setChangedTitle(props.obj.title)}>Change Title</button>
        </div>
        }
      </div>
      <div className = "flex justify-start">
        <p className = "date">{props.obj.date}</p>
      </div>
      </>
      : 
      <div>
        <input type="text" value={changedTitle} onChange={(e) => setChangedTitle(e.target.value)}/>
        <button type="button" onClick={() => changeTitle()}>+</button>
      </div>}
    </div>
  );
};

export default TaskCard;