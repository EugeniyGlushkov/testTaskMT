import React from 'react';
import { Link, withRouter } from 'react-router-dom';

class Update extends React.Component {
    constructor(props) {
        super(props);
        this.state = {id: '', name: '', email:'', password:'', birthDate:''};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        fetch('/api/user/get/' + this.props.match.params.id)
            .then(response => {
                return response.json();
            }).then(result => {
            console.log(result);
            this.setState({
                id:result.id,
                name:result.name,
                email:result.email,
                password:result.password,
                birthDate:result.birthDate
            });
        });
    }
    handleChange(event) {
        const state = this.state
        state[event.target.name] = event.target.value
        this.setState(state);
    }
    handleSubmit(event) {
        event.preventDefault();
        fetch('/api/user/update', {
            method: 'POST',
            body: JSON.stringify({
                id:this.state.id,
                name: this.state.name,
                email: this.state.email,
                password: this.state.password,
                birthDate: this.state.birthDate
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            if(response.status === 200) {
                alert("User update successfully.");
            }
        });
    }

    render() {
        return (
            <div id="container">
                <Link to="/">Users</Link>
                <p/>
                <form onSubmit={this.handleSubmit}>
                    <input type="hidden" name="id" value={this.state.id}/>
                    <p>
                        <label>Name:</label>
                        <input type="text" name="name" value={this.state.name} onChange={this.handleChange} placeholder="Name" />
                    </p>
                    <p>
                        <label>Email:</label>
                        <input type="text" name="email" value={this.state.email} onChange={this.handleChange} placeholder="Email" />
                    </p>
                    <p>
                        <label>Password:</label>
                        <input type="text" name="password" value={this.state.password} onChange={this.handleChange} placeholder="Password" />
                    </p>
                    <p>
                        <label>BirthDate:</label>
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

export default Update;