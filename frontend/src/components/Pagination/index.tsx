import ReactPaginate from "react-paginate";
import { ReactComponent as ArrowIcon } from "assets/images/arrow.svg";
import "./styles.css";

const Pagination = () => {
  return (
    <ReactPaginate
      pageCount={10}
      pageRangeDisplayed={3}
      marginPagesDisplayed={1}
      containerClassName="pagination-container"
      pageLinkClassName="pagination-item"
      breakClassName="pagination-item"
      previousClassName="arrow-previous"
      nextClassName="arrow-next"
      activeLinkClassName="pagination-link-active"
      disabledClassName="arrow-inactive"
      previousLabel={<ArrowIcon />}
      nextLabel={<ArrowIcon />}
    />
  );
};

export default Pagination;
