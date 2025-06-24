import { useState, useEffect } from 'react';
import SideBar from './SideBar';
import MainContent from './MainContent';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './index.css';

export const TASK_URL = import.meta.env.VITE_TASK_URL;
export const DAYS_URL = import.meta.env.VITE_DAYS_URL;

export interface TaskCardProps{
    title: string,
    date: string,
    days: string[],
}

function App()
{
    const [component, setComponent] = useState<string | null>(null);
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

    return (
        <div className = "container">
            <SideBar className = "sideBar"  setComponent = {setComponent} tasks = {tasks} setTasks = {setTasks}/>
            <MainContent className = "mainBar" selectedComponent = {component} setTasks={setTasks} setComponent={setComponent}/>
        </div>
    );
}

export default App;