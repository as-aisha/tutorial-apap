import React from "react";
import Button from "../button";
import classes from "./styles.module.css";

const Item = (props) => {
    const { id, title, price, description, category, quantity, handleEdit, handleAddItemToCart, handleDelete } = props;
    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`Stok: ${quantity}`}</p>
            <Button action={handleEdit}>Edit</Button>
            <div className="space-x-2">
                <input 
                className="px-2 w-64 rounded-md h-auto border-2 border-blue-700"
                type="number"
                placeholder="Enter qty..."
                id={`cartItem${id}`}
                name="cartItem"
                />
                <Button action={handleAddItemToCart}>Add to Cart</Button>
            </div>
        </div>
    );
};
export default Item;