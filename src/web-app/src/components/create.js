import React from 'react';
import { Link } from 'react-router-dom';

class Create extends React.Component {
    constructor(props) {
        super(props);
        this.state = {name: '', email:'', birthDate:''};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange(event) {
        const state = this.state
        state[event.target.name] = event.target.value
        this.setState(state);
    }
    handleSubmit(event) {
        event.preventDefault();
        fetch('http://10.2.226.115:9999/api/user/add', {
            method: 'POST',
            body: JSON.stringify({
                name: this.state.name,
                email: this.state.email,
                birthDate: this.state.birthDate
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            if(response.status === 200) {
                alert("New user saved successfully");
            }
        });
    }
    render() {
        return (
            <div id="container">
                <Link to="/api/">Users</Link>
                <p/>
                <form onSubmit={this.handleSubmit}>
                    <p>
                        <label>Name:</label>
                        <input type="text" name="name" value={this.state.name} onChange={this.handleChange} placeholder="Name" />
                    </p><p>
                        <label>Title:</label>
                        <input type="text" name="email" value={this.state.email} onChange={this.handleChange} placeholder="Email" />
                    </p>
                    <p>
                        <label>URL:</label>
                        <input type="text" name="birthDate" value={this.state.birthDate} onChange={this.handleChange} placeholder="BirthDate" />
                    </p>
                    <p>
                        <input type="submit" value="Submit" />
                    </p>
                </form>
            </div>
        );
    }
}

export default Create;