import { Box, Stack } from "@mui/material";
import { Form, Formik } from "formik";
import { loginInitialValues, loginValidtions } from "./const";
import LoginForm from "./LoginForm";

const Login = () => {
  const handleSubmit = () => {
    console.log("In handle submit");
  };

  return (
    <Box alignContent="center" alignItems="center">
      <Formik
        initialValues={loginInitialValues}
        onSubmit={handleSubmit}
        validationSchema={loginValidtions}
      >
        <Form>
          <LoginForm />
        </Form>
      </Formik>
    </Box>
  );
};

export default Login;
