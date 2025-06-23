import React, { useState } from 'react';
import SideBar from './SideBar';
import MainContent from './MainContent';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './index.css';

function App()
{
    const [component, setComponent] = useState<string | null>(null);

    return (
        <div className = "container">
            <SideBar className = "sideBar"  selectedComponent = {setComponent}/>
            <MainContent className = "mainBar" selectedComponent = {component}/>
        </div>
    );
}

export default App;