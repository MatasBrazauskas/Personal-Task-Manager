import React, { useState } from 'react';
import { TASK_URL, DAYS_URL, type TaskCardProps } from './App';

const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

interface taskTable{
    title:string,
    description:string,
    date:string,
    status:boolean,
}

interface dayTable{
    title:string,
    week_days:string[],
}

interface Props{
    setTasks: React.Dispatch<React.SetStateAction<TaskCardProps[]>>,
    setComponent: React.Dispatch<React.SetStateAction<string | null>>,
}

const AddNewTask: React.FC<Props> = (props: Props) => {
  const [tasksName, setTasksName] = useState<string>('');
  const [description, setDescription] = useState<string>('');
  const [daysArray, setArray] = useState<string[]>([]);

  const addRemoveDay = (day:string) => {
      const index = daysArray.indexOf(day);

      if(index === -1){
          setArray(arr => [...arr, day]);
      }else{
          setArray(arr => arr.filter(i => i !== day))
      }
  }

  const handleSumbit = async (e:React.FormEvent) => {
      e.preventDefault();

      const date = new Date();
      const taskObj:taskTable = {
        title: tasksName,
        description: description,
        date: `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`,
        status: false,
      };
      
      const responce = await fetch(TASK_URL, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(taskObj),
      });

      if(responce.ok){
          const obj: TaskCardProps = {
              title:taskObj.title,
              date: taskObj.date,
              days: daysArray,
          }
          props.setTasks(arr => [...arr, obj]);
      }
      else{
        console.log("Can't add a object.")
        return;
      }
      

      const daysObj: dayTable = {
          title: tasksName,
          week_days:daysArray,
      }

      console.log(DAYS_URL);

      const daysResponce = await fetch(DAYS_URL, {
          method:'POST',
          headers: {
              "Content-Type": "application/json",
          },
          body: JSON.stringify(daysObj),
      });

      if(!daysResponce.ok){
        console.log("Can' add days to table.")
      }
  }

  return (
    <>
        <form onSubmit = {(e) => handleSumbit(e)}>
            <input
                type="text"
                onChange={(e) => setTasksName(e.target.value)}
                placeholder="Enter Task Name"
                className='form-control'
            /> 

            <input
                type="text"
                onChange={(e) => setDescription(e.target.value)}
                placeholder='Enter Description For A Task'
                className='form-control'
            />

            {days.map((day, index) => (
                <div key={index}>
                    <input
                        type="checkbox"
                        id={day}
                        value={day.toUpperCase()}
                        onChange={(e) => addRemoveDay(e.target.value)}
                        name="daysOfWeek"
                        className = 'form-check-input'
                    />
                    <label htmlFor={day}>{day}</label>
                </div>
            ))}
            

            <button type="submit" className = 'btn btn-primary'>Submit</button>
        </form>
    </>
  );
};

export default AddNewTask;