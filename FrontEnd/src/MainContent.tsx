import React from 'react';
import AddNewTask from './AddNewTask';
import { ADD_TASK_KEY } from './SideBar';

type MainContentProps = {
    className: string;
    selectedComponent: string | null;
}

const MainBar: React.FC<MainContentProps> = (props: MainContentProps) => {

    return (
        <div className = {props.className}>
        {
            props.selectedComponent === ADD_TASK_KEY && <AddNewTask/>
        }
        </div>
    );
};

export default MainBar;