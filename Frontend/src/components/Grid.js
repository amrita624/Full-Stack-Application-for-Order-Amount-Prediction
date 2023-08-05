import React, { useState, useEffect } from "react";
import { DataGrid } from "@mui/x-data-grid";
import { makeStyles } from "@material-ui/core/styles";
import AdvancedSearch from "./AdvancedSearch";

const useStyles = makeStyles({
  root: {
    "& .MuiDataGrid-root": {
      fontFamily: "Roboto, RobotoDraft, Helvetica, Arial, sans-serif",
      color: "white",
      backgroundColor: "#666666",
      margin: 0,
    },
    "& .MuiDataGrid-row": {
      textAlign: "left",
      fontSize: "13px",
      borderBottom: "1px solid #fff",
      color: "white",
    },
    "& .MuiDataGrid-cell": {
      border: "none",
    },
    "& .MuiDataGrid-columnHeaderTitle": {
      textAlign: "left",
      overflow: "visible",
      // margin: "0",
      border: "none",
      padding: "0",
      fontWeight: "bold",
      lineHeight: "1.5",
      fontSize: "14px",
      whiteSpace: "normal",
      textOverflow: "clip",
      color: "white",
    },
    "& .MuiDataGrid-iconSeparator": {
      display: "none",
    },
    "& .MuiSvgIcon-root": {
      color: "white",
    },
    "& .MuiDataGrid-columnHeaderTitleContainer": {
      padding: 0,
    },
    "& .MuiDataGrid-footerContainer": {
      borderTop: "1px solid #fff",
      color: "white",
    },
    "& .MuiTablePagination-caption": {
      color: "white",
    },
    "& .MuiSelect-select.MuiSelect-select": {
      color: "white",
    },
    "&::-webkit-scrollbar": {
      width: "16px",
    },
    "&::-webkit-scrollbar-track": {
      borderRradius: "8px",
      margin: "7rem",
    },
    "&::-webkit-scrollbar-thumb": {
      height: "56px",
      borderRadius: "8px",
      border: "4px solid transparent",
      backgroundClip: "content-box",
      backgroundColor: "#888",
    },
    "&::-webkit-scrollbar-thumb:hover": {
      background: "#555",
    },
  },
});

const Grid = () => {
  const classes = useStyles();
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(
        "http://localhost:8080/H2H_backend/DataLoadingServlet"
      );
      const data = await response.json();
      setData(data);
    };

    fetchData();
  }, []);

  const handleSearch = () => {
    // Apply the filtering logic based on the search query
    const filtered = data.filter((row) => {
      // Modify the condition based on your specific search criteria and data structure
      return row.customerOrderId.toLowerCase().includes(searchQuery.toLowerCase());
    });

    setFilteredData(filtered);
  };

  const handleAdvancedSearch = (searchCriteria) => {
    // Apply the filtering logic based on the advanced search criteria
    const filtered = data.filter((row) => {
      // Modify the conditions based on your specific search criteria and data structure
      return (
        row.customerOrderId.toLowerCase().includes(searchCriteria.customerOrderId.toLowerCase()) &&
        row.customerNumber.toLowerCase().includes(searchCriteria.customerNumber.toLowerCase()) &&
        row.salesOrg.toLowerCase().includes(searchCriteria.salesOrg.toLowerCase())
        // Add more conditions for additional fields
      );
    });

    setFilteredData(filtered);
  };

  const handleAddData = () => {
    // Logic to add the new data to the dataset
    const updatedData = [...data, newData];
    setData(updatedData);
    setFilteredData(updatedData);
    setShowAddDataDialog(false);
    setNewData({
      customerOrderId: "",
      salesOrg: "",
      distributionChannel: "",
      customerNumber: "",
      companyCode: "",
      orderCurrency: "",
      amountInUSD: "",
      orderCreationDate: "",
    });
  };

  const handleInputChange = (e) => {
    setNewData({ ...newData, [e.target.name]: e.target.value });
  };

  const handleDateChange = (e) => {
    setNewData({ ...newData, orderCreationDate: e.target.value });
  };

  const handleEditData = () => {
    // Logic to edit the selected row data
    const updatedData = data.map((row) => {
      if (selectedRowIds.includes(row.id)) {
        return {
          ...row,
          customerOrderId: editData.customerOrderId,
          salesOrg: editData.salesOrg,
          distributionChannel: editData.distributionChannel,
          customerNumber: editData.customerNumber,
          companyCode: editData.companyCode,
          orderCurrency: editData.orderCurrency,
          amountInUSD: editData.amountInUSD,
          orderCreationDate: editData.orderCreationDate,
        };
      }
      return row;
    });

    setData(updatedData);
    setFilteredData(updatedData);
    setShowEditDataDialog(false);
    setEditData({
      customerOrderId: "",
      salesOrg: "",
      distributionChannel: "",
      customerNumber: "",
      companyCode: "",
      orderCurrency: "",
      amountInUSD: "",
      orderCreationDate: "",
    });
    setSelectedRowIds([]);
    setSnackbarMessage("Data updated successfully.");
    setSnackbarOpen(true);
  };

  const handleDeleteData = () => {
    // Logic to delete the selected row(s)
    const updatedData = data.filter((row) => !selectedRowIds.includes(row.id));

    setData(updatedData);
    setFilteredData(updatedData);
    setShowDeleteConfirmation(false);
    setSelectedRowIds([]);
    setSnackbarMessage("Data deleted successfully.");
    setSnackbarOpen(true);
  };

  const handleEditInputChange = (event) => {
    const { name, value } = event.target;
    setEditData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleEditDateChange = (event) => {
    const { name, value } = event.target;
    setEditData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleCheckboxChange = (event) => {
    const { checked, value } = event.target;
    let updatedSelectedRowIds = [];

    if (checked) {
      updatedSelectedRowIds = [...selectedRowIds, value];
    } else {
      updatedSelectedRowIds = selectedRowIds.filter(
        (rowId) => rowId !== value
      );
    }

    setSelectedRowIds(updatedSelectedRowIds);
  };

  const handleSelectAllCheckboxChange = (event) => {
    const { checked } = event.target;
    let updatedSelectedRowIds = [];

    if (checked) {
      updatedSelectedRowIds = filteredData.map((row) => row.id);
    }

    setSelectedRowIds(updatedSelectedRowIds);
  };

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  const columns = [
    { field: "sl_no", headerName: "Sl No", width: 60 },
    { field: "cust_order_id", headerName: "CUSTOMER ORDER ID", width: 120 },
    { field: "sales_org", headerName: "SALES ORG", width: 85 },
    { field: "dist_chan", headerName: "DISTRIBUTION CHANNEL", width: 240 },
    { field: "comp_code", headerName: "COMPANY CODE", width: 110 },
    {
      field: "order_creation_date",
      headerName: "ORDER CREATION DATE",
      width: 218,
    },
    { field: "order_curr", headerName: "ORDER CURRENCY", width: 112 },
    { field: "cust_num", headerName: "CUSTOMER NUMBER", width: 120 },
    { field: "amount_in_usd", headerName: "AMOUNT IN USD", width: 180 },
  ];

  return (
    <div
      className={classes.root}
      style={{
        width: "100%",
        height: 452,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
            <div>
        <TextField
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          label="Customer Order Id"
        />
        <Button variant="contained" color="primary" onClick={handleSearch}>
          Search
        </Button>
        <Button
          variant="outlined"
          color="primary"
          onClick={() => setShowAdvancedSearch(!showAdvancedSearch)}
        >
          Advanced Search
        </Button>
      </div>
      {showAdvancedSearch && <AdvancedSearch onSearch={handleAdvancedSearch} />}
      
      <DataGrid
        rows={data}
        columns={columns}
        className={classes.root}
        getRowId={(row) => row.sl_no}
        pageSize={8}
        rowsPerPageOptions={[5, 10, 20, 50, 100]}
        rowHeight={40}
        checkboxSelection
        disableSelectionOnClick
        hideFooterSelectedRowCount
      />
      <Dialog
        open={showAddDataDialog}
        onClose={() => setShowAddDataDialog(false)}
      >
        <DialogTitle>Add Data</DialogTitle>
        <DialogContent>
          <TextField
            name="customerOrderId"
            label="Customer Order Id"
            value={newData.customerOrderId}
            onChange={handleInputChange}
          />
          <TextField
            name="salesOrg"
            label="Sales Org"
            value={newData.salesOrg}
            onChange={handleInputChange}
          />
          <TextField
            name="distributionChannel"
            label="Distribution Channel"
            value={newData.distributionChannel}
            onChange={handleInputChange}
          />
          <TextField
            name="customerNumber"
            label="Customer Number"
            value={newData.customerNumber}
            onChange={handleInputChange}
          />
          <TextField
            name="companyCode"
            label="Company Code"
            value={newData.companyCode}
            onChange={handleInputChange}
          />
          <TextField
            name="orderCurrency"
            label="Order Currency"
            value={newData.orderCurrency}
            onChange={handleInputChange}
          />
          <TextField
            name="amountInUSD"
            label="Amount in USD"
            value={newData.amountInUSD}
            onChange={handleInputChange}
          />
          <TextField
            name="orderCreationDate"
            label="Order Creation Date"
            type="date"
            value={newData.orderCreationDate}
            onChange={handleDateChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowAddDataDialog(false)}>Cancel</Button>
          <Button onClick={handleAddData}>Add</Button>
        </DialogActions>
      </Dialog>
      {/* Edit Data Dialog */}
      <Dialog open={showEditDataDialog}>
        <DialogTitle>Edit Data</DialogTitle>
        <DialogContent>
          {/* Implement your edit data fields here */}
          {/* For example: */}
          <TextField
            label="Customer Order ID"
            name="customerOrderId"
            value={editData.customerOrderId}
            onChange={handleEditInputChange}
          />
          {/* Add more fields based on your requirements */}
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowEditDataDialog(false)}>Cancel</Button>
          <Button onClick={handleEditData}>Save</Button>
        </DialogActions>
      </Dialog>

      {/* Delete Confirmation Dialog */}
      <Dialog open={showDeleteConfirmation}>
        <DialogTitle>Delete Confirmation</DialogTitle>
        <DialogContent>
          Are you sure you want to delete the selected data?
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowDeleteConfirmation(false)}>
            Cancel
          </Button>
          <Button onClick={handleDeleteData}>Delete</Button>
        </DialogActions>
      </Dialog>

      {/* Snackbar for notifications */}
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={3000}
        onClose={handleSnackbarClose}
      >
        <MuiAlert
          elevation={6}
          variant="filled"
          severity="success"
          onClose={handleSnackbarClose}
        >
          {snackbarMessage}
        </MuiAlert>
      </Snackbar>
    </div>
  );
};

export default Grid;