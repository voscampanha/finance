'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {accounts: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/accounts'}).done(response => {
            debugger
			this.setState({accounts: response.entity._embedded.accounts});
		});
	}

	render() {
		return (
			<AccountList accounts={this.state.accounts}/>
		)
	}
}

class AccountList extends React.Component{
	render() {
		const accounts = this.props.accounts.map(account =>
			<Account key={account._links.self.href} account={account}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Owner</th>
						<th>Priority</th>
					</tr>
					{accounts}
				</tbody>
			</table>
		)
	}
}

class Account extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.account.name}</td>
				<td>{this.props.account.owner}</td>
				<td>{this.props.account.priority}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)