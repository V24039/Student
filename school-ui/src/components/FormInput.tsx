import { TextField } from "@mui/material";
import { useField } from "formik";
import { IFormCommonProps } from "types";

interface IFormInputProps extends Omit<IFormCommonProps, "defaultValue"> {
  required: boolean;
}

const FormInput = ({
  label,
  palceHolder,
  required,
  ...props
}: IFormInputProps) => {

  const [field, meta] = useField(props);

  return (
    <TextField
      fullWidth
      required
      placeholder={palceHolder}
      id={field.name}
      name={field.name}
      label={label}
      onChange={field.onChange}
      onBlur={field.onChange}
      error={meta.touched && Boolean(meta.error)}
      helperText={meta.touched && meta.error}
      
    />
  );
};

export default FormInput;
