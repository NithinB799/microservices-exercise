import ProductListPage from './pages/ProductListPage';
import AddProductPage from './pages/AddProductPage';
import CartPage from './pages/CartPage';

function App() {
    return (
        <div>
            <h1>Enterprise Product Management</h1>

            <AddProductPage />

            <hr />

            <ProductListPage />

            <hr />

            <CartPage />
        </div>
    );
}

export default App;