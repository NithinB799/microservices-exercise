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
            const matchesName = product.name
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
        <div>
            <h2>Product List</h2>

            <div>
                <label>Search by Name: </label>
                <input
                    type="text"
                    value={searchText}
                    onChange={(event) => setSearchText(event.target.value)}
                    placeholder="Search product"
                />
            </div>

            <br />

            <div>
                <label>Filter by Max Price: </label>
                <input
                    type="number"
                    value={maxPrice}
                    onChange={(event) => setMaxPrice(event.target.value)}
                    placeholder="Enter max price"
                />
            </div>

            <br />

            {loading && <LoadingSpinner />}

            {error && <ErrorMessage message={error} />}

            <table border="1" cellPadding="10">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                {filteredProducts.map((product) => (
                    <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.price}</td>
                        <td>{product.stock}</td>
                        <td>
                            <button onClick={() => handleAddToCart(product.id)}>
                                Add to Cart
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <br />

            <div>
                <button onClick={handlePrevious} disabled={currentPage === 0}>
                    Previous
                </button>

                {Array.from({ length: totalPages }, (_, index) => (
                    <button
                        key={index}
                        onClick={() => setCurrentPage(index)}
                        disabled={currentPage === index}
                    >
                        {index + 1}
                    </button>
                ))}

                <button onClick={handleNext} disabled={currentPage === totalPages - 1}>
                    Next
                </button>
            </div>
        </div>
    );
}

export default ProductListPage;