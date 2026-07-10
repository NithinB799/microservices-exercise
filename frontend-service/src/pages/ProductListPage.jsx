import { useMemo, useState } from 'react';
import useProducts from '../hooks/useProducts';
import useAddToCart from '../hooks/useAddToCart';
import LoadingSpinner from '../components/LoadingSpinner';
import ErrorMessage from '../components/ErrorMessage';

function ProductListPage() {
    const [currentPage, setCurrentPage] = useState(0);
    const [searchText, setSearchText] = useState('');
    const [maxPrice, setMaxPrice] = useState('');

    const pageSize = 5;
    const sortBy = 'id';

    const { products, totalPages, loading, error } =
        useProducts(currentPage, pageSize, sortBy);

    const { handleAddToCart } = useAddToCart();

    const filteredProducts = useMemo(() => {
        return products.filter((product) => {
            const productName = product.name || '';

            const matchesName = productName
                .toLowerCase()
                .includes(searchText.toLowerCase());

            const matchesPrice =
                maxPrice === '' || Number(product.price) <= Number(maxPrice);

            return matchesName && matchesPrice;
        });
    }, [products, searchText, maxPrice]);

    const handlePrevious = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    const handleNext = () => {
        if (currentPage < totalPages - 1) {
            setCurrentPage(currentPage + 1);
        }
    };

    return (
        <section className="page-section">
            <div className="page-heading">
                <div>
                    <h2>Products</h2>
                    <p>Browse, search, filter, and add products to the cart.</p>
                </div>
            </div>

            <div className="toolbar">
                <div className="field">
                    <label>Search by name</label>
                    <input
                        type="text"
                        value={searchText}
                        onChange={(event) => setSearchText(event.target.value)}
                        placeholder="Example: Laptop"
                    />
                </div>

                <div className="field">
                    <label>Max price</label>
                    <input
                        type="number"
                        value={maxPrice}
                        onChange={(event) => setMaxPrice(event.target.value)}
                        placeholder="Example: 1000"
                    />
                </div>
            </div>

            {loading && <LoadingSpinner />}

            {error && <ErrorMessage message={error} />}

            <div className="card">
                <div className="table-wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Action</th>
                        </tr>
                        </thead>

                        <tbody>
                        {filteredProducts.length === 0 ? (
                            <tr>
                                <td colSpan="5" className="empty-state">
                                    No products found.
                                </td>
                            </tr>
                        ) : (
                            filteredProducts.map((product) => (
                                <tr key={product.id}>
                                    <td>{product.id}</td>
                                    <td>
                                        <strong>{product.name || 'Unnamed product'}</strong>
                                    </td>
                                    <td>${Number(product.price || 0).toLocaleString()}</td>
                                    <td>
                                            <span className="badge">
                                                {product.stock ?? 0} in stock
                                            </span>
                                    </td>
                                    <td>
                                        <button onClick={() => handleAddToCart(product.id)}>
                                            Add to Cart
                                        </button>
                                    </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </table>
                </div>
            </div>

            <div className="pagination">
                <button onClick={handlePrevious} disabled={currentPage === 0}>
                    Previous
                </button>

                {Array.from({ length: totalPages }, (_, index) => (
                    <button
                        key={index}
                        onClick={() => setCurrentPage(index)}
                        disabled={currentPage === index}
                        className={currentPage === index ? 'page-active' : ''}
                    >
                        {index + 1}
                    </button>
                ))}

                <button onClick={handleNext} disabled={currentPage === totalPages - 1}>
                    Next
                </button>
            </div>
        </section>
    );
}

export default ProductListPage;