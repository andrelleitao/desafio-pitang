import SearchIcon from "../../Icon/SearchIcon";
import Loading from "../Loading";

function SearchButton(props) {
    return (
        <button 
            onClick={props.onClick}
            type="button" className="btn btn-primary" >
            {
                props.loading ? <Loading/> : <SearchIcon/>
            }
        </button>
    );
}

export default SearchButton;