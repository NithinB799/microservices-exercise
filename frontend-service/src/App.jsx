import ProductListPage from './pages/ProductListPage';
import AddProductPage from './pages/AddProductPage';

function App() {
  return (
      <div>
        <h1>Enterprise Product Management</h1>

        <AddProductPage />

        <hr />

        <ProductListPage />
      </div>
  );
}

export default App;