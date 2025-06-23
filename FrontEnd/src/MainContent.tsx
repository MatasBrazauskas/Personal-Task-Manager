import React, { useState, useEffect } from 'react';
import AddNewTask from './AddNewTask';
const ADD_TASK_KEY:string = import.meta.env.ADD_NEW_TASK_MESSAGE; 

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