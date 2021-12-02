import React from "react";
import classes from "./styles.module.css";

const CartItem = props => {
    const { id, title, price, jumlah, description, category } = props;
    return (
        <div className={classes.item}>
            <h3>{`ID Cart: ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Jumlah: ${jumlah}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <h3>{`Total Harga: ${parseFloat(price) * parseFloat(jumlah)}`}</h3>
        </div>
    );
};

export default CartItem;