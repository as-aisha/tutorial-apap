import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import { Badge } from '@mui/material';
import Fab from '@mui/material/Fab';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import ViewStreamIcon from '@mui/icons-material/ViewStream';
import { Link } from "react-router-dom";

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,
            cartItems: [],
            cartHidden: true,
        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleAddItemToCart = this.handleAddItemToCart.bind(this);
    }

    componentDidMount() {
        this.loadData();
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }

    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    handleAddItem() {
        this.setState({ isCreate: true });
    }

    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate: false, isEdit: false });
    }

    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.setState({
                items: [],
                isLoading: false,
                isCreate: false,
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    handleSearchItem = event => {
        const query = event.target.value;
        this.handleInputSearch(query);
    }

    async handleInputSearch(query){
        const response = await APIConfig.get("/item");
        const results = response.data.result;
        if (query.trim() === "") {
            this.loadData();
        } else {
            let searchedItems = results.filter(
                item => item.title.toLowerCase().includes(query.toLowerCase())
            );
            this.setState({
                items: searchedItems
            });
        }
    }

    async loadDataCart() {
        try {
            const { data } = await APIConfig.get("/cart");
            this.setState({ cartItems: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleAddItemToCart(event) {
        const inputValue = document.getElementById("cartItem" + event.id).value;
        const cartItems = [...this.state.cartItems];
        try {
            if (cartItems.length === 0) {
                if (inputValue <= event.quantity) {
                    const data = {
                        quantity: inputValue,
                        idItem: event.id,
                    };
                    await APIConfig.post("/cart", data);
                    this.loadData();
                    this.loadDataCart();
                }
            } else {
                for (let i = 0; i < cartItems.length; i++) {
                    const temp = cartItems[i];
                    if (event.id === temp.item.id) {
                        if (inputValue <= event.quantity - temp.quantity) {
                            const data = {
                                quantity: inputValue,
                                idItem: event.id,
                            };
                            await APIConfig.post("/cart", data);
                            this.loadData();
                            this.loadDataCart();
                        } else {
                            alert("Insufficient stock");
                        }
                    } else {
                        console.log("masuk else");
                        if (inputValue <= event.quantity) {
                            const data = {
                                quantity: inputValue,
                                idItem: event.id,
                            };
                            await APIConfig.post("/cart", data);
                        }
                    }
                    alert("Oops maaf stok tidak cukup");
                }
            }
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    render() {
        return (
            <div className={classes.itemList}>
                <h1 className={classes.title}>
                    All Items
                </h1>
                <div style={{position:'fixed', top:25, right:25}}>
                    <Link to="/cart">
                        <Fab variant="extended">
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                        </Fab>
                    </Link>
                </div>
                <input
                    className={classes.textField}
                    type="text"
                    placeholder="Search..."
                    aria-label="Search"
                    onChange={this.handleSearchItem}
                />
                <Button action={this.handleAddItem}>
                    Add Item
                </Button>
                <div>
                    {this.state.items.map((item) => (
                        <Item
                            key={item.id}
                            id={item.id}
                            title={item.title}
                            price={item.price}
                            description={item.description}
                            category={item.category}
                            quantity={item.quantity}
                            handleEdit={ () => this.handleEditItem(item) }
                        />
                    ))}
                </div>
                <Modal
                    show={this.state.isCreate || this.state.isEdit}
                    handleCloseModal={this.handleCancel}
                    modalTitle={this.state.isCreate
                        ? "Add Item"
                        :`Edit Item ID ${this.state.id}`}
                >
                    <form>
                        <input 
                        className={classes.textField}
                        type="text"
                        placeholder="Nama Item"
                        name="title"
                        value={this.state.title}
                        onChange={this.handleChangeField}
                        />
                        <input 
                        className={classes.textField}
                        type="number"
                        placeholder="Harga"
                        name="price"
                        value={this.state.price}
                        onChange={this.handleChangeField}
                        />
                        <textarea 
                        className={classes.textField}
                        placeholder="Deskripsi"
                        name="description"
                        rows="4"
                        value={this.state.description}
                        onChange={this.handleChangeField}
                        />
                        <input 
                        className={classes.textField}
                        type="text"
                        placeholder="Kategori"
                        name="category"
                        value={this.state.category}
                        onChange={this.handleChangeField}
                        />
                        <input 
                        className={classes.textField}
                        type="number"
                        placeholder="qty"
                        name="quantity"
                        value={this.state.quantity}
                        onChange={this.handleChangeField}
                        />
                        <Button action={this.state.isCreate
                            ? this.handleSubmitItem
                            : this.handleSubmitEditItem}
                        >
                            Create
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal>
            </div>
        );
    }
}
export default ItemList;
