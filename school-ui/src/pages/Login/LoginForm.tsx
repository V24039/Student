import { Button, Container, Link, Stack, Typography } from "@mui/material"
import FormInput from "components/FormInput"
import LoginPassword from "components/LoginPassword"

const LoginForm = () => {
  return (
    <Container maxWidth="xs" sx={{ mt: 25 }}>
      <Stack spacing={2} display="flex">
        <Typography component="h1" variant="h3" color="GrayText" align="center">
          Sign In
        </Typography>
        <FormInput
          required
          label="LoginId/Email"
          name="loginId"
          palceHolder="Enter your Login or email"
        />
        <LoginPassword
          label="Password"
          name="password"
          palceHolder="Enter your Password"
        />
        <Button type="submit" variant="contained" color="secondary">
          Login
        </Button>
        <Link href="#">Forgot Password/LoginID</Link>
      </Stack>
    </Container>
  )
}

export default LoginForm
