import React from 'react';
import AddNewTask from './AddNewTask';
import { ADD_TASK_KEY } from './SideBar';
import type { TaskCardProps } from './App';

type MainContentProps = {
    className: string;
    selectedComponent: string | null;
    setTasks: React.Dispatch<React.SetStateAction<TaskCardProps[]>>,
    setComponent: React.Dispatch<React.SetStateAction<string | null>>,
}

const MainBar: React.FC<MainContentProps> = (props: MainContentProps) => {

    return (
        <div className = {props.className}>
        {
            props.selectedComponent === ADD_TASK_KEY && <AddNewTask setTasks={props.setTasks} setComponent={props.setComponent}/>
        }
        </div>
    );
};

export default MainBar;