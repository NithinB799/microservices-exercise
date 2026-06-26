import { Link } from 'react-router-dom';
import AppRoutes from './routes/AppRoutes';

function App() {
    return (
        <div>
            <h1>Enterprise Product Management</h1>

            <nav>
                <Link to="/products">Products</Link>
                {' | '}
                <Link to="/add-product">Add Product</Link>
                {' | '}
                <Link to="/cart">Cart</Link>
            </nav>

            <hr />

            <AppRoutes />
        </div>
    );
}

export default App;