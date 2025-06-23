import React from 'react';
import TaskCard from './TaskCard';
import "./TaskCard.css";
import type { TaskCardProps } from './App';

export const ADD_TASK_KEY = import.meta.env.VITE_ADD_NEW_TASK_MESSAGE;

interface SideBarProps{
    className:string,
    setComponent: React.Dispatch<React.SetStateAction<string | null>>;
    tasks: TaskCardProps[],
    setTasks: React.Dispatch<React.SetStateAction<TaskCardProps[]>>;
}

const SideBar: React.FC<SideBarProps> = (props: SideBarProps) =>
{
    return (
        <div className = {props.className}>
            <div>
                {props.tasks.map((task, index) => (
                    <TaskCard
                        key={index}
                        className = "task"
                        obj = {task}
                        setTasks={props.setTasks}
                    />
                ))}
            </div>
            {<div>
                <button type = "button" className = "btn btn-outline-secondary" onClick={() => {props.setComponent(ADD_TASK_KEY), alert(ADD_TASK_KEY)}}>Add New Task</button>
            </div>}
        </div>
    );
};

export default SideBar;
