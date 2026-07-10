import { NavLink } from 'react-router-dom';
import AppRoutes from './routes/AppRoutes';

function App() {
    return (
        <div className="app">
            <header className="app-header">
                <div className="navbar">
                    <div className="brand">
                        <span className="brand-icon">◈</span>

                        <div>
                            <h1>ProductHub</h1>
                            <p>Enterprise Product Management</p>
                        </div>
                    </div>

                    <nav className="nav-links">
                        <NavLink
                            to="/products"
                            className={({ isActive }) =>
                                isActive ? 'nav-link active' : 'nav-link'
                            }
                        >
                            Products
                        </NavLink>

                        <NavLink
                            to="/add-product"
                            className={({ isActive }) =>
                                isActive ? 'nav-link active' : 'nav-link'
                            }
                        >
                            Add Product
                        </NavLink>

                        <NavLink
                            to="/cart"
                            className={({ isActive }) =>
                                isActive ? 'nav-link active' : 'nav-link'
                            }
                        >
                            Cart
                        </NavLink>
                    </nav>
                </div>
            </header>

            <main className="main-content">
                <AppRoutes />
            </main>
        </div>
    );
}

export default App;