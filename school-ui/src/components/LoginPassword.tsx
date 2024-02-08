import { Visibility, VisibilityOff } from "@mui/icons-material";
import { Icon, IconButton, InputAdornment, TextField } from "@mui/material";
import { useField } from "formik";
import { useState } from "react";
import { IFormCommonProps } from "types";

interface ILoginPasswordProps extends Omit<IFormCommonProps, "defaultValue"> {}

const LoginPassword = ({
  label,
  palceHolder,
  ...props
}: ILoginPasswordProps) => {
  const [showPassword, setShowPassword] = useState<boolean>(false);

  const [field, meta] = useField(props);

  const handleShowPassword = () => {
    setShowPassword((prev) => !prev);
  };

  return (
    <TextField
      fullWidth
      required
      type={showPassword ? "text" : "password"}
      placeholder={palceHolder}
      id={field.name}
      name={field.name}
      label={label}
      onChange={field.onChange}
      onBlur={field.onChange}
      error={meta.touched && Boolean(meta.error)}
      helperText={meta.touched && meta.error}
      InputProps={{
        endAdornment: (
          <InputAdornment position="end">
            <IconButton
              aria-label="toggle password visibility"
              onClick={handleShowPassword}
              edge="end"
            >
              {showPassword ? <VisibilityOff /> : <Visibility />}
            </IconButton>
          </InputAdornment>
        ),
      }}
    />
  );
};

export default LoginPassword;
