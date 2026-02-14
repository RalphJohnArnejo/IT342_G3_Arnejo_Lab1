import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const API = 'http://localhost:8080'

export default function Dashboard() {
    const navigate = useNavigate()
    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)
    const [loggingOut, setLoggingOut] = useState(false)

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const res = await fetch(`${API}/api/user/me`, {
                    credentials: 'include',
                })
                if (!res.ok) {
                    navigate('/')
                    return
                }
                const data = await res.json()
                setUser(data)
            } catch {
                navigate('/')
            } finally {
                setLoading(false)
            }
        }
        fetchUser()
    }, [navigate])

    const handleLogout = async () => {
        setLoggingOut(true)
        try {
            await fetch(`${API}/api/auth/logout`, {
                method: 'POST',
                credentials: 'include',
            })
        } catch {
            // ignore
        }
        navigate('/')
    }

    if (loading) {
        return (
            <div className="card">
                <h1>Loading…</h1>
                <p className="subtitle" style={{ marginBottom: 0 }}>
                    <span className="spinner"></span> Fetching your profile
                </p>
            </div>
        )
    }

    if (!user) return null

    const initial = user.username ? user.username.charAt(0).toUpperCase() : '?'

    return (
        <div className="card wide">
            <h1>Dashboard</h1>
            <p className="subtitle">Your profile overview</p>

            <div className="profile-info">
                <div className="profile-avatar">{initial}</div>
                <div className="profile-username">{user.username}</div>
                <div className="profile-id">User ID: {user.id}</div>
            </div>

            <div className="profile-detail">
                <span className="detail-label">Username</span>
                <span className="detail-value">{user.username}</span>
            </div>

            <div className="profile-detail">
                <span className="detail-label">User ID</span>
                <span className="detail-value">#{user.id}</span>
            </div>

            <div className="profile-detail">
                <span className="detail-label">Status</span>
                <span className="detail-value" style={{ color: '#6ee7b7' }}>● Active</span>
            </div>

            <button
                className="btn btn-danger"
                onClick={handleLogout}
                disabled={loggingOut}
                style={{ marginTop: 24 }}
            >
                {loggingOut ? <><span className="spinner"></span>Logging out…</> : 'Logout'}
            </button>
        </div>
    )
}
