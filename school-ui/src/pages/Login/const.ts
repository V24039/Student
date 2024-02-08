import { object, string, number, date, InferType } from 'yup';

export const loginInitialValues = {
    logindId: '',
    password: ''
}

export const loginValidtions = object({
    loginId: string().required("No Id provided"),
    password: string().required("Enter your password")
})