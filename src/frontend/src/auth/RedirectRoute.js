import React, { Children } from 'react'
import { Navigate } from 'react-router-dom'
import { UserAuth } from '../auth/AuthContext'

const RedirectRoute = ({children}) => {

    const {user} = UserAuth();
    if(!user){
        return children;
    }else if (user.email==="simon@simon.com"){
      return <Navigate to="/"/>
    }else{
      return <Navigate to="/userView"/>
  }
}

export default RedirectRoute