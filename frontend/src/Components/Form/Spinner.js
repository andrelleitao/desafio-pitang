function Spinner(props) {
    return (
        <>
        {
            props.show && (
                <div className={props.className}>
                    <i className="fas fa-sync fa-spin"></i>
                </div>
            )
        }
        </>
    );
}

export default Spinner;