import { useEffect, useMemo, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts } from '../features/productSlice';
import { addCartItem } from '../features/cartSlice';
import LoadingSpinner from '../components/LoadingSpinner';
import ErrorMessage from '../components/ErrorMessage';

function ProductListPage() {
    const dispatch = useDispatch();

    const { products, loading, error } = useSelector((state) => state.products);

    const [searchText, setSearchText] = useState('');
    const [maxPrice, setMaxPrice] = useState('');

    useEffect(() => {
        dispatch(fetchProducts());
    }, [dispatch]);

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

    const handleAddToCart = async (productId) => {
        const cartItem = {
            cartId: 4,
            productId: productId,
            quantity: 1
        };

        await dispatch(addCartItem(cartItem));

        alert('Product added to cart successfully!');
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
        </div>
    );
}

export default ProductListPage;