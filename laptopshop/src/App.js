import React from "react"
import MainRoutes from "./routes/main-routes";
import { SessionContextProvider } from './hooks/session-context/session-context-provider';

const App = () => {
  return (
        <SessionContextProvider>
          <MainRoutes />
        </SessionContextProvider>
    );
};

export default App;

