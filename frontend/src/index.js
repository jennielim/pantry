import React, { useEffect, useState } from "react";
import { createRoot } from "react-dom/client";
import { useFormik } from "formik";
import "./App.css";
import * as Yup from 'yup';

const ItemEntry = () => {

    const [items, setitems] = useState([])
    const [sort, setsort] = useState('')

    const changeSort = (e) => {
        setsort(e.target.value)
        const sortby = e.target.value
        console.log(sortby)
        if (sortby === "na") {
            fetch('http://localhost:8080/pantry/sortNameAsc', { method: 'get' })
                .then(response => {
                    return response.json()
                })
                .then(data => {
                    setitems(data)
                })
        }
        else if (sortby === "qa") {
            fetch('http://localhost:8080/pantry/sortQuantityAsc', { method: 'get' })
                .then(response => {
                    return response.json()
                })
                .then(data => {
                    setitems(data)
                })
        }
        else if (sortby === "qd") {
            fetch('http://localhost:8080/pantry/sortQuantityDesc', { method: 'get' })
                .then(response => {
                    return response.json()
                })
                .then(data => {
                    setitems(data)
                })
        }
    }

    const deleteAll = () => {
        fetch('http://localhost:8080/pantry/deleteAll', { method: 'delete' })
            .then(() => {
                fetchData() // error block
            })
    }

    const formik = useFormik({
        initialValues: {
            name: '',
            option: true,
            quantity: '',
            category: ''
        },

        validationSchema: Yup.object({
            name: Yup.string()
                .required('Item name is required'),
            option: Yup.boolean()
                .required('Option is required'),
            quantity: Yup.number()
                .min(1, 'Number greater than 0 is required')
                .required(),
            category: Yup.string()
                .required('Category is required'),
        }),

        onSubmit: values => {
            // environment variable
            fetch('http://localhost:8080/pantry/add', {
                method: 'post',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(values)
            }).then(() => {
                fetchData() // error block
            }).catch((error) => {
                console.error(error)
            })
        },
    })

    const fetchData = () => {
        fetch('http://localhost:8080/pantry/getAll', { method: 'get' })
            .then(response => {
                return response.json()
            })
            .then(data => {
                setitems(data)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])

    return (
        <>
          <h1> Pantry </h1>
          <div className="mainContent">
              <div className="subContent">
                  <form onSubmit={formik.handleSubmit}>
                      {/* Item Name */}
                      <h5 htmlFor="name">Name</h5>
                      <input
                          id="name"
                          name="name"
                          type="inputField"
                          onChange={formik.handleChange}
                          value={formik.values.name.charAt(0).toUpperCase() + formik.values.name.slice(1)}
                          onBlur={formik.handleBlur} />
                      {formik.touched.name && formik.errors.name ? (<div className="error">{formik.errors.name}</div>) : null}
                      {/* Option */}
                      <h5 htmlFor="option"> Option </h5>
                      <div style={{ display: "inline-flex" }}>
                          <label style={{ padding: "5px" }}>
                              <input
                                  name="option"
                                  type="radio"
                                  onChange={() => formik.setFieldValue("option", true)}
                                  onBlur={formik.handleBlur} />
                              Add
                          </label>
                          <label style={{ padding: "5px" }}>
                              <input
                                  name="option"
                                  type="radio"
                                  onChange={() => formik.setFieldValue("option", false)}
                                  onBlur={formik.handleBlur} />
                              Remove
                          </label>
                      </div>
                      {formik.touched.option && formik.errors.option ? (<div className="error">{formik.errors.option}</div>) : null}
                      {/* Quantity */}
                      <h5 htmlFor="quantity">Quantity</h5>
                      <input
                          id="quantity"
                          name="quantity"
                          type="number"
                          onChange={formik.handleChange}
                          value={formik.values.quantity}
                          onBlur={formik.handleBlur} />
                      {formik.touched.quantity && formik.errors.quantity ? (<div className="error">{formik.errors.quantity}</div>) : null}
                      {/* Category */}
                      <h5 htmlFor="category">Category</h5>
                    <select
                        name="category"
                        value={formik.values.category}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}>
                        <option value="">
                        Select a category
                        </option>
                        <option value="Fruit">
                        Fruit
                        </option>
                        <option value="Vegetable">
                        Vegetable
                        </option>
                        <option value="Meat">
                        Meat
                        </option>
                        <option value="Dairy">
                        Dairy
                        </option>
                        <option value="Bakery">
                        Bakery
                        </option>
                        <option value="Other">
                        Other
                        </option>
                    </select>
                    {formik.touched.category && formik.errors.category ? (<div className="error">{formik.errors.category}</div>) : null}
                    <div className="mainContent">
                        <button type="submit">Submit</button>
                    </div>
                </form>
            </div>
          </div>
            <div className="mainContent">
              <div style={{ textAlign: "right", width: "50%" }} className="subContent">
                  <select style={{ width: "100px", fontSize: "80%" }} value={sort} onChange={changeSort}>
                      <option value="">
                          Sort By
                      </option>
                      <option value="na">
                          Name A-Z
                      </option>
                      <option value="qa">
                          Quantity ↑
                      </option>
                      <option value="qd">
                          Quantity ↓
                      </option>
                  </select>
              </div>
          </div>
          <div className="mainContent">
              <div style={{ width: "50%" }} className="subContent">
                  <div className="subSubContent">
                      <h3>Items</h3>
                      {items.map(items => (
                          <div key={items.id}>
                              {items.name.charAt(0).toUpperCase() + items.name.slice(1)}
                          </div>
                      ))}
                  </div>
                  <div className="subSubContent">
                      <h3>Quantity</h3>
                      {items.map(items => (
                          <div key={items.id}>
                              {items.quantity}
                          </div>
                      ))}
                  </div>
                  <div className="subSubContent">
                      <h3>Category</h3>
                      {items.map(items => (
                          <div key={items.id}>
                              {items.category.charAt(0).toUpperCase() + items.category.slice(1)}
                          </div>
                      ))}
                  </div>
                  <button style={{ marginTop: "1rem", padding: "0.02rem 0.8rem", fontSize: "80%", float: "right" }} onClick={deleteAll}> Delete All </button>
            </div>
        </div>
    </>
  );
};

function App() {
    return <ItemEntry />
}

const rootElement = document.getElementById("root")
const root = createRoot(rootElement)

root.render(<App />)

// SELECT * FROM pantry;