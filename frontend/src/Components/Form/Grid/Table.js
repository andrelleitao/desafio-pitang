function Table(props) {
    return (
        <div className="row">
            <div className="col-sm-12">
                <div className="table-responsive">
                    <table className="table mt-2">
                        {props.children}
                    </table>
                </div>
            </div>
        </div>
    );
}

export default Table;