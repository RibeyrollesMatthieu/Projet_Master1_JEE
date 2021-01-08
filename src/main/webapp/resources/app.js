async function postAcceptRequest(id) { $.post('relation-update?accept=' + id); }
async function postDelete(id) { $.post('relation-update?delete=' + id); }
async function postCancelRequest(id) { $.post('relation-update?cancel=' + id); }
async function postDeclineRequest(id) { $.post('relation-update?decline=' + id); }
async function postSendRequest(id) { $.post('relation-update?add=' + id); }

const acceptFriendRequest = (id) => {
  postAcceptRequest(id)
    .then(reloadFriendSearch)
    .catch(() => console.log("Cannot post the accept friend request"));
}
const deleteFriend = (id) => {
  postDelete(id)
    .then(reloadFriendSearch)
    .catch(() => console.log("Cannot post the delete friend request."));
}
const cancelFriendRequest = (id) => {
  postCancelRequest(id)
    .then(reloadFriendSearch)
    .catch(() => console.log("Cannot post the cancel friend request."));
}
const declineFriendRequest = (id) => {
  postDeclineRequest(id)
    .then(reloadFriendSearch)
    .catch(() => console.log("Cannot post the decline friend request"));
}
const sendFriendRequest = (id) => {
  postSendRequest(id)
    .then(reloadFriendSearch)
    .catch(() => console.log("Cannot post the send friend request"));
}

//FIXME back to last tab when we do an action on a searched user
const searchUser = (user) => {
  if (user.trim().length === 0) {
    searchDisplay.classList.add('hidden');
    friends.classList.remove('hidden');
    pending.classList.remove('hidden');
  } else {
    if (searchDisplay.classList.contains('hidden')) searchDisplay.classList.remove('hidden');
    if (! friends.classList.contains('hidden')) {
      friends.classList.add('hidden');
      pending.classList.add('hidden');
    }
    $.get('friends?search=' + user);
  }

  reloadFriendSearch();
}
const changeTab = (isFriends) => {
  const active = (isFriends ? friends : pending).classList.contains('active');
  if (isFriends) {
    if (active) {
      if (pending.classList.contains('active')) changeStatus(friends, false);
    } else {  // swap side, friends is now active
      window.history.pushState({}, '', '?cat=friends');
      reloadFriendSearch();
      changeStatus(friends, true);
      changeStatus(pending, false);
    }
  } else {
    if (active) {
      if (friends.classList.contains('active')) changeStatus(pending, false);
    } else {  //swap sides, pending is ow active
      window.history.pushState({}, '', '?cat=pending');
      reloadFriendSearch();
      changeStatus(pending, true);
      changeStatus(friends, false);
    }
  }
}