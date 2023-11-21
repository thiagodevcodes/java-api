export function controlModal(modal, isOpen, setModalOpen) {
    setModalOpen({
        post: modal === "post" ? isOpen : false,
        update: modal === "update" ? isOpen : false
    });
}