import fetch from "unfetch";

const checkStatus = (response) => {
  if (response.ok) {
    return response;
  }
  const error = new Error(response.statusText);
  error.response = response;
  return Promise.reject(error);
};

export const getAllRows = (prop) => fetch(`api/v1/${prop}`).then(checkStatus);

export const addNewEntry = (type, info) =>
  fetch(`api/v1/${type}`, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(info),
  });

export const deleteEntry = (entry, entryId) =>
  fetch(`api/v1/${entry}/${entryId}`, {
    method: "DELETE",
  }).then(checkStatus);

  export const getEntry = (entry, entryId) =>
  fetch(`api/v1/${entry}/${entryId}`).then(checkStatus);

export const getDogCount = () =>
  fetch(`api/v1/dashboard`).then(checkStatus);

export const getOwnersIdAndName = () =>
  fetch(`api/v1/owners/ownersDropdown`).then(checkStatus);

  export const getOwnerPetList = (id) =>
  fetch(`api/v1/pets/allPetsOfOwner/${id}`).then(checkStatus);