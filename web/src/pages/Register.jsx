import { useState } from 'react'
import { Link } from 'react-router-dom'

const API = 'http://localhost:8080'

export default function Register() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [msg, setMsg] = useState({ text: '', type: '' })
    const [loading, setLoading] = useState(false)

    const handleSubmit = async (e) => {
        e.preventDefault()
        if (!username.trim() || !password) {
            setMsg({ text: 'Please fill in all fields.', type: 'error' })
            return
        }

        setLoading(true)
        setMsg({ text: '', type: '' })

        try {
            const res = await fetch(`${API}/api/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                credentials: 'include',
                body: JSON.stringify({ username: username.trim(), password }),
            })
            const text = await res.text()

            if (res.ok) {
                setMsg({ text: text || 'Registration successful! You can now sign in.', type: 'success' })
                setUsername('')
                setPassword('')
            } else {
                setMsg({ text: text || 'Registration failed. Please try again.', type: 'error' })
            }
        } catch {
            setMsg({ text: 'Could not connect to the server. Is the backend running?', type: 'error' })
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="card">
            <h1>Create Account</h1>
            <p className="subtitle">Register a new account to get started</p>

            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username</label>
                <input
                    type="text"
                    id="username"
                    placeholder="Choose a username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    autoComplete="off"
                    required
                />

                <label htmlFor="password">Password</label>
                <input
                    type="password"
                    id="password"
                    placeholder="Create a password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />

                <button className="btn" type="submit" disabled={loading}>
                    {loading ? <><span className="spinner"></span>Registering…</> : 'Register'}
                </button>
            </form>

            {msg.text && <div className={`message ${msg.type}`}>{msg.text}</div>}

            <div className="footer">
                Already have an account? <Link to="/">Sign in</Link>
            </div>
        </div>
    )
}
