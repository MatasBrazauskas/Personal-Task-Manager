import React, { useState } from 'react';

const months: string[] = [
  '-', 'January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December'
];
export const TASK_URL: string = 'http://localhost:8080/tasks';

const AddNewTask: React.FC = () => {
  const [tasksName, setTasksName] = useState<string>('');
  const [selectedMonth, setSelectedMonth] = useState<number>(new Date().getMonth());
  const [selectedDay, setSelectedDay] = useState<number>(new Date().getDay() + 1);
  const [selectedYear, setSelectedYear] = useState<number>(new Date().getFullYear());

  const getDaysInMonth = (month: number): number => {
    if (month === 2) return 28;
    return [4, 6, 9, 11].includes(month) ? 30 : 31;
  };

  const currentYear = new Date().getFullYear();
  const years = ['-'];
  for (let i = 0; i < 4; i++) {
    years.push(String(currentYear + i));
  }

  const handleSumbit = async (e: React.FormEvent) => {
    e.preventDefault();

    const existCheckUrl = `${TASK_URL}/exist?title=${tasksName}`;
    const responce = await fetch(existCheckUrl, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        },
    });

    console.log(responce);
    const bool = await Boolean(responce.json());

    if(responce.ok && bool === true){
      const taskPayload = {
        title: tasksName,
        status: false,
        dueDate: `${selectedYear}-${String(selectedMonth).padStart(2, '0')}-${String(selectedDay).padStart(2, '0')}`
    };
        const newResponce = await fetch(TASK_URL, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(taskPayload),
        });

        if(!newResponce.ok){
          console.log(`Can't add a task - {obj}`);
        }

    }else{
      console.log(responce);
        console.log("Name already exist");
    }

  };

  return (
    <>
        <form onSubmit = {(e) => handleSumbit(e)}>
            <input
                type="text"
                onChange={(e) => setTasksName(e.target.value)}
                placeholder="Enter Task Name"
            />
            <select onChange={(e) => setSelectedMonth(parseInt(e.target.value))}>
                {months.map((month, i) => (
                <option key={i} value={i}>{month}</option>
                ))}
            </select>
            <select onChange = {(e) => setSelectedDay(parseInt(e.target.value))}>
                {[...Array(getDaysInMonth(selectedMonth))].map((_, i) => (
                <option key={i + 1} value={i + 1}>{i + 1}</option>
                ))}
            </select>
            <select onChange={(e) => setSelectedYear(parseInt(e.target.value))}>
                {years.map((year, i) => (
                <option key={i} value={year}>{year}</option>
                ))}
            </select>

            <button type="submit">Submit</button>
        </form>
    </>
  );
};

export default AddNewTask;