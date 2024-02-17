import { MENU_ROUTE } from "./constants/menuRoute";

export const menuClienteConfig = {
    "id": "car",
    "trans": "cars",
    "icon": "fa fa-car",
    "items": [
        {
            "trans": "new", 
            "navigate": MENU_ROUTE.CAR_NEW
        },
        {
            "trans": "list", 
            "navigate": MENU_ROUTE.CAR
        }
    ]    
};