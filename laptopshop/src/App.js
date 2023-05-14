import React from "react"
import MainRoutes from "./routes/main-routes";
import { SessionContextProvider } from './hooks/session-context/session-context-provider';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <SessionContextProvider>
          <MainRoutes />
        </SessionContextProvider>
      </header>
    </div>
  );
}

export default App;
