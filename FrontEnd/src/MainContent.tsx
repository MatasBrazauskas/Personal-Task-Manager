import React, { useState } from 'react';
import AddNewTask from './AddNewTask';
import LoadingBar from './LoadingBar';
import TasksToComplete from './TasksToComplete';
import { ADD_TASK_KEY } from './SideBar';
import type { TaskCardProps } from './App';
import './MainCont.css';

type MainContentProps = {
    className: string;
    selectedComponent: string | null;
    setTasks: React.Dispatch<React.SetStateAction<TaskCardProps[]>>,
    setComponent: React.Dispatch<React.SetStateAction<string | null>>,
}

const MainBar: React.FC<MainContentProps> = (props: MainContentProps) => {

    const [taskCount, setTaskCount] = useState(0);
    const [completedTaskCount, setTaskCompletedCount] = useState(0);

    return (
    <div className = {props.className}>
        <LoadingBar count={taskCount} completed={completedTaskCount}/>
        <TasksToComplete setTaskCount={setTaskCount} setTaskCompletedCount={setTaskCompletedCount}/>
        <div className = {props.className}>
        {
            props.selectedComponent === ADD_TASK_KEY && <AddNewTask setTasks={props.setTasks} setComponent={props.setComponent}/>
        }
        </div>
    </div>
    );
};

export default MainBar;