import { useRef, useContext, useState, useEffect } from 'react';
import axios from './api/axios';
import AuthContext from './context/AuthProvider';

const LOGIN_URL = 'merchants/login';

const Login = () => {
    const { setAuth } = useContext(AuthContext);
    const errRef = useRef();

    const [email, setEmail] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setMsgErr] = useState('');
    const [passwordShown, setPasswordShown] = useState(false);
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        setMsgErr('');
    }, [email, pwd]);

    // Password toggle handler
    const togglePassword = () => {
        // When the handler is invoked
        // inverse the boolean state of passwordShown
        setPasswordShown(!passwordShown);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(LOGIN_URL,
                JSON.stringify({ email, pwd }),
                {
                    headers: { 'content-type': 'application/json' }
                }
            );
            console.log(JSON.stringify(response?.data));
            // Access token
            const token = response?.data?.token;
            const merchantId = response?.data?.id;
            // Pass data
            setAuth({ email, pwd, merchantId, token });
            setEmail('');
            setPwd('');
            setSuccess(true);
        } catch (err) {
            if (!err?.response) {
                setMsgErr('No Server Response');
            } else if (err.response?.status === 400) {
                setMsgErr('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setMsgErr('Unauthorized');
            } else {
                setMsgErr('Login Failed');
            }
            errRef.current.focus();
        }
    }

    return (
        <>
            {success ? (
                <section>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <a>Go to Home</a>
                    </p>
                </section>
            ) : (
                <section>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h2>Foodpay Cashier - Sign In</h2>
                    <form onSubmit={handleSubmit}>
                        <label>Email</label>
                        <input
                            type="email"
                            id="email"
                            autoComplete="off"
                            onChange={ (e) => setEmail(e.target.value) }
                            value={email}
                            required
                        />
                        <label>Password</label>
                        <input
                            type={ passwordShown ? "text" : "password" }
                            id="password"
                            onChange={ (e) => setPwd(e.target.value) }
                            value={pwd}
                            required
                        />
                        <checkbox onClick={togglePassword}><a>Show Password</a></checkbox>
                        <button type='submit'>Sign In</button>
                    </form>
                </section >
            )}
        </>
    )
}

export default Login;