/**
 * Flag seleção true ou false padrão da aplicação.
 */
function SwitchButton(props) {
    return (
        <div
            className="custom-control custom-switch">
            <input type="checkbox" className="custom-control-input" id={props.id} checked={props.checked} onChange={props.onChange} />
            <label className="custom-control-label" htmlFor={props.id}>{props.text}</label>
        </div>
    );
}

export default SwitchButton;