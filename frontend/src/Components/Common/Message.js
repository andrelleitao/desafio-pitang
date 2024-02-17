import { toast } from "react-toastify";

export const warn = (msg) => {
    toast.warn(msg);
}

export const error = (msg) => {
    toast.error(msg);
}

export const success = (msg) => {
    toast.success(msg);
}