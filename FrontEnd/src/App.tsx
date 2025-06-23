import { useState, useEffect } from 'react';
import SideBar from './SideBar';
import MainContent from './MainContent';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './index.css';

export const URL = import.meta.env.VITE_URL;

export interface TaskCardProps{
    title: string,
    status: boolean,
    dueDate: string,
}

function App()
{
    const [component, setComponent] = useState<string | null>(null);
    const [tasks, setTasks] = useState<TaskCardProps[]>([]);

    useEffect(() => {
        console.log(URL);
        const fetchedTasks = async () => {
            const responce = await fetch(URL);

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
            <MainContent className = "mainBar" selectedComponent = {component}/>
        </div>
    );
}

export default App;