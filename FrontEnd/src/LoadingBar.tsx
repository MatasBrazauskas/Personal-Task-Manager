import React, { useState, useEffect } from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import './Loading.css';

type Props = {
    count:number,
    completed:number
};

const ProgressBar:React.FC<Props> = (props: Props) => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    console.log(`Count - ${props.count}`);
    console.log(`$Completed tasks - ${props.completed}`);
    if(props.count === 0 || props.completed === 0)
    {
        setProgress(0);
    }
    else{
        setProgress(Math.min(Math.round(props.completed / props.count) * 100, 100));
    }
  },[props.completed, props.count])

  return (
    <div className='loading'>
        <Box sx={{ width: '100%', p: 2 }}>
            <Typography variant="h6">Progress: {progress}%</Typography>
            <LinearProgress variant="determinate" value={progress} />
        </Box>
    </div>
  );
}

export default ProgressBar;