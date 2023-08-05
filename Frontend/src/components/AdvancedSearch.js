import React, { useState } from "react";
import {
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
} from "@mui/material";

const AdvancedSearch = ({ onSearch }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [searchCriteria, setSearchCriteria] = useState({
    customerOrderId: "",
    customerNumber: "",
    salesOrg: "",
  });

  const handleOpenDialog = () => {
    setIsOpen(true);
  };

  const handleCloseDialog = () => {
    setIsOpen(false);
  };

  const handleInputChange = (e) => {
    setSearchCriteria({ ...searchCriteria, [e.target.name]: e.target.value });
  };

  const handleSearch = () => {
    onSearch(searchCriteria);
    handleCloseDialog();
  };

  return (
    <div>
      <Button variant="contained" color="primary" onClick={handleOpenDialog}>
        Advanced Search
      </Button>
      <Dialog open={isOpen} onClose={handleCloseDialog}>
        <DialogTitle>Advanced Search</DialogTitle>
        <DialogContent>
          <TextField
            type="text"
            name="customerOrderId"
            value={searchCriteria.customerOrderId}
            onChange={handleInputChange}
            label="Customer Order Id"
            fullWidth
          />
          <TextField
            type="text"
            name="customerNumber"
            value={searchCriteria.customerNumber}
            onChange={handleInputChange}
            label="Customer Number"
            fullWidth
          />
          <TextField
            type="text"
            name="salesOrg"
            value={searchCriteria.salesOrg}
            onChange={handleInputChange}
            label="Sales Org"
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Cancel</Button>
          <Button onClick={handleSearch} variant="contained" color="primary">
            Search
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default AdvancedSearch;
