import React from 'react';
import { Link } from 'react-router-dom';

class Users extends React.Component {
    constructor(props) {
        super(props);
        this.state = {users: []};
        this.headers = [
            { key: 'id', label: 'Id'},
            { key: 'name', label: 'Name'},
            { key: 'email', label: 'Email' },
            { key: 'password', label: 'Password' },
            { key: 'birthDate', label: 'BirthDate' }
        ];
        this.deleteUser = this.deleteUser.bind(this);
    }

    componentDidMount() {
        fetch('/api/users')
            .then(response => {
                return response.json();
            }).then(result => {
            console.log(result);
            this.setState({
                users:result
            });
        });
    }

    deleteUser(id) {
        if(window.confirm("Are you sure want to delete?")) {
            fetch('/api/user/delete/' + id)
                .then(response => {
                    if(response.status === 200) {
                        alert("User deleted successfully");
                        fetch('/api/users')
                            .then(response => {
                                return response.json();
                            }).then(result => {
                            console.log(result);
                            this.setState({
                                websites:result
                            });
                        });
                    }
                });
        }
        window.location.reload();
    }

    render() {
        return (
            <div id="container">
                <Link to="/create">Add User</Link>
                <p/>
                <table>
                    <thead>
                    <tr>
                        {
                            this.headers.map(function(h) {
                                return (
                                    <th key = {h.key}>{h.label}</th>
                                )
                            })
                        }
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.users.map(function(item, key) {
                            return (
                                <tr key = {key}>
                                    <td>{item.id}</td>
                                    <td>{item.name}</td>
                                    <td>{item.email}</td>
                                    <td>{item.birthDate}</td>
                                    <td>
                                        <Link to={`/update/${item.id}`}>Edit   </Link>

                                        <a href="javascript:void(0);" onClick={this.deleteUser.bind(this, item.id)}>Delete</a>
                                    </td>
                                </tr>
                            )
                        }.bind(this))
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Users;