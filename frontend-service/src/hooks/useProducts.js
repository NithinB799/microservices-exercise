import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPaginatedProducts } from '../features/productSlice';

function useProducts(currentPage, pageSize, sortBy) {
    const dispatch = useDispatch();

    const { products, totalPages, loading, error } = useSelector(
        (state) => state.products
    );

    useEffect(() => {
        dispatch(fetchPaginatedProducts({ page: currentPage, size: pageSize, sortBy }));
    }, [dispatch, currentPage, pageSize, sortBy]);

    return {
        products,
        totalPages,
        loading,
        error
    };
}

export default useProducts;