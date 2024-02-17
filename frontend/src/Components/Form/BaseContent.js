import Header from "./Header";

function BaseContent(props) {
    return (
        <div className="col-lg-12 col-md-12 col-sm-12 mt-4">
            <div className="card">
                <div className="header">
                    <Header
                        icon={props.header.icon}
                        headerText={props.header.text}
                        mainNavigate={props.header.mainNavigate}
                        currentPage={props.header.currentPage}
                        childNav={props.header.childNav}
                        showGrid={false}
                        gridRoute={props.header.route}
                    />
                </div>
                <div className="body">
                    {props.child}
                </div>
            </div>
        </div>
    );
}

export default BaseContent;